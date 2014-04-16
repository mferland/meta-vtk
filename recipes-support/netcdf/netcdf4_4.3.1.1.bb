DESCRIPTION = "The NetCDF C-library"
HOMEPAGE = "http://www.unidata.ucar.edu/software/netcdf/"
SECTION = "libs"
LICENSE = "license_netcdf"
LIC_FILES_CHKSUM = "file://COPYRIGHT;md5=2919588cd602f2c92fba38e3ceb172f2"

DEPENDS = "hdf5"

SRC_URI = "ftp://ftp.unidata.ucar.edu/pub/netcdf/netcdf-${PV}.tar.gz"

S = "${WORKDIR}/netcdf-${PV}"

SRC_URI[md5sum] = "275c3b839088674c2f00fb3ac264bf11"
SRC_URI[sha256sum] = "09a4123d631714f488a2dc43292a7218e5241f2cf9288d2dbc8347d2fe176cad"

inherit autotools pkgconfig

EXTRA_OECONF += "--enable-netcdf4"
