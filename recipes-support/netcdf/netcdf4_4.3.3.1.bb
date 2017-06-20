DESCRIPTION = "The NetCDF C-library"
HOMEPAGE = "http://www.unidata.ucar.edu/software/netcdf/"
SECTION = "libs"
LICENSE = "license_netcdf"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=1c92f58b414789214b804e296facf959"

DEPENDS = "hdf5 curl zlib"

SRC_URI = "ftp://ftp.unidata.ucar.edu/pub/netcdf/netcdf-${PV}.tar.gz"

S = "${WORKDIR}/netcdf-${PV}"

SRC_URI[md5sum] = "5c9dad3705a3408d27f696e5b31fb88c"
SRC_URI[sha256sum] = "bdde3d8b0e48eed2948ead65f82c5cfb7590313bc32c4cf6c6546e4cea47ba19"

inherit autotools pkgconfig

EXTRA_OECONF += "--enable-netcdf4"

do_install_append() {
    # remove configuration summary file
    rm -f ${D}${libdir}/libnetcdf.settings
}
