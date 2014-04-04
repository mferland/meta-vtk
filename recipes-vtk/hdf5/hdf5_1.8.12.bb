DESCRIPTION = "The HDF5 library (Hierarchical Data Format)"
HOMEPAGE = "http://www.hdfgroup.org"
SECTION = "libs"
LICENSE = "license_hdf5"
LIC_FILES_CHKSUM = "file://COPYING;md5=4ad878f669af233d3b861433c71857e4"

SRC_URI = "http://www.hdfgroup.org/ftp/HDF5/current/src/${PN}-${PV}.tar.bz2 \
file://acsite_fixes.patch \
file://configure_fix.patch \
"

SRC_URI[md5sum] = "03ad766d225f5e872eb3e5ce95524a08"
SRC_URI[sha256sum] = "6d080f913a226a3ce390a11d9b571b2d5866581a2aa4434c398cd371c7063639"

inherit autotools pkgconfig

CACHED_CONFIGUREVARS += "\
${@base_contains('DISTRO_FEATURES', 'largefile', 'hdf5_cv_have_lfs=yes', 'hdf5_cv_have_lfs=no', d)} \
hdf5_cv_gettimeofday_tz=yes \
hdf5_cv_vsnprintf_works=yes \
hdf5_cv_system_scope_threads=yes \
hdf5_cv_direct_io=no \
hdf5_cv_ldouble_to_integer_works=yes \
hdf5_cv_ulong_to_float_accurate=yes \
hdf5_cv_fp_to_ullong_accurate=yes \
hdf5_cv_fp_to_ullong_right_maximum=yes \
hdf5_cv_ldouble_to_uint_accurate=yes \
hdf5_cv_ullong_to_ldouble_precision=yes \
hdf5_cv_fp_to_integer_overflow_works=yes \
hdf5_cv_ldouble_to_long_special=no \
hdf5_cv_long_to_ldouble_special=no \
hdf5_cv_ldouble_to_llong_accurate=yes \
hdf5_cv_llong_to_ldouble_correct=yes \
"

EXTRA_OECONF += "--enable-shared --disable-static"

PACKAGES += "${PN}-examples"
FILES_${PN}-examples = "${datadir}"
FILES_${PN}-dev += "${libdir}/libhdf5.settings"
